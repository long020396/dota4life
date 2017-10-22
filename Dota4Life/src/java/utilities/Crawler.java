package utilities;

import BLO.HeroBLO;
import BLO.RoleBLO;
import BLO.RoleOfHeroBLO;
import BLO.SkillBLO;
import entities.Hero;
import entities.Role;
import entities.RoleOfHero;
import entities.Skill;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crawler {

    private List<Hero> heroList;
    private List<Role> roleList;
    private Map<String, String> linksOfHeroes_DotaWiki;
    private Map<String, String> linksOfHeroes_Devilesk;
    private Map<String, String> linksOfCounterHeroes;

    public Crawler() {
        this.heroList = new ArrayList<Hero>();
        this.roleList = new ArrayList<Role>();
        this.linksOfHeroes_DotaWiki = new HashMap<String, String>();
        this.linksOfHeroes_Devilesk = new HashMap<String, String>();
        this.linksOfCounterHeroes = new HashMap<String, String>();
    }

    public void crawlDataAndSaveToDB() {
        RoleBLO roleBLO = new RoleBLO();
        HeroBLO heroBLO = new HeroBLO();
        SkillBLO skillBLO = new SkillBLO();
        RoleOfHeroBLO roleOfHeroBLO = new RoleOfHeroBLO();

        getListOfHero();
        crawlHero();
        for (Hero hero : this.heroList) {
            hero.setImg(Utils.downloadImage(ConstantManager.PATH_MEDIA, "\\" + hero.getName(), hero.getName(), hero.getImg()));
            for (Skill skill : hero.getSkillList()) {
                skill.setImg(Utils.downloadImage(ConstantManager.PATH_MEDIA, "\\" + hero.getName(), skill.getName(), skill.getImg()));
            }
            heroBLO.add(hero);
        }
        crawlCounterHeroes();
        for (Hero hero : this.heroList) {
            heroBLO.update(hero);
        }
        crawlRole();
        for (Role role : this.roleList) {
            role.setRoleOfHeroList(null);
        }
        for (Hero hero : this.heroList) {
            hero.setRoleOfHeroList(null);
        }
        for (Role role : this.roleList) {
            roleBLO.add(role);
        }
        crawlRole();
        for (Role role : this.roleList) {
            roleBLO.update(role);
        }
    }

    public void getListOfHero() {
        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(ConstantManager.URL_DEVILESK_HEROES);

            boolean correctContent = false;
            boolean heroEntry = false;

            String line = null;
            String heroName = null;
            String heroLink = null;

            int attrInt = 0;
            int divClassRow = 1;

            while ((line = bReader.readLine()) != null) {

                if (correctContent) {
                    if (line.contains("hero-block")) {
                        attrInt++;
                    }

                    if (heroEntry) {
                        heroName = Utils.getHtmlAttributeValue(line, "title");

                        /* Set link for heroes in Devilesk */
                        this.linksOfHeroes_Devilesk.put(heroName, heroLink);

                        /* Set link for heroes in Devilesk */
                        String tmpHeroName = heroName.replaceAll(" ", "_");
                        this.linksOfHeroes_DotaWiki.put(heroName, ConstantManager.URL_DOTA2WIKI + "/" + tmpHeroName);
                        this.linksOfCounterHeroes.put(heroName, ConstantManager.URL_DOTA2WIKI + "/" + tmpHeroName + "/Counters");

                        /* Create hero */
                        Hero hero = new Hero();
                        hero.setName(heroName);
                        switch (attrInt) {
                            case 1:
                                hero.setAttribute("Strength");
                                break;
                            case 2:
                                hero.setAttribute("Agility");
                                break;
                            default:
                                hero.setAttribute("Intelligence");
                        }
                        this.heroList.add(hero);

                        heroEntry = false;
                    }

                    if (line.contains("class=\"hero\"")) {
                        heroEntry = true;
                        heroLink = ConstantManager.URL_DEVILESK_HOME + Utils.getHtmlAttributeValue(line, "href");
                    }
                }

                if (line.contains("class=\"row")) {
                    if (divClassRow == 2) {
                        correctContent = true;
                    }
                    divClassRow++;
                }

                if (correctContent && line.contains("</section")) {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crawlRole() {
        String document = getRoleDataFromHtml(ConstantManager.URL_DOTA2WIKI_ROLE);

        try {
            StAXParser.parseXMLRolesOfHeroes(document, this.heroList, this.roleList, !this.roleList.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crawlHero() {
        int count = 0;
        for (Map.Entry<String, String> entry : this.linksOfHeroes_Devilesk.entrySet()) {
            String document = getHeroDataFromHtml(entry.getValue());

            Hero hero = null;
            int index = -1;

            for (Hero each : this.heroList) {
                index++;
                if (each.getName().equals(entry.getKey())) {
                    hero = each;
                    break;
                }
            } // end for heroList

            try {
                /* parse data by StAX */
                StAXParser.parseXMLHeroData(document, hero);

                /* Set back hero to ArrayList */
                this.heroList.set(index, hero);

                /* Sleep for 2s to prevent DDoS protection */
                count++;
                if (count % 50 == 0) {
                    Thread.sleep(1000);
                }

            } catch (InterruptedException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } // end for linksOfHeroes.entrySet()
    }

    public void crawlCounterHeroes() {
        int count = 0;
        for (Map.Entry<String, String> entry : this.linksOfCounterHeroes.entrySet()) {
            String document = getCounterHeroesFromHtml(entry.getValue());

            Hero hero = null;
            int index = -1;

            for (Hero each : this.heroList) {
                index++;
                if (each.getName().equals(entry.getKey())) {
                    hero = each;
                    break;
                }
            } // end for heroList

            try {
                /* Parse data by StAX */
                StAXParser.parseXMLCounterHeroData(document, this.heroList, hero);

                /* Set back hero to ArrayList */
                this.heroList.set(index, hero);

                /* Sleep for 2s to prevent DDoS protection */
                count++;
                if (count % 50 == 0) {
                    Thread.sleep(1000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String getRoleDataFromHtml(String url) {
        String doc = "";

        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(url);

            String line;
            boolean correctContent = false;

            while ((line = bReader.readLine()) != null) {
                if (line.contains("<span class=\"mw-headline\" id=\"Unofficial_roles\">Unofficial roles</span>")) {
                    break;
                }

                if (correctContent) {
                    doc += line.trim();
                }

                if (line.contains("<span class=\"mw-headline\" id=\"Official_roles\">Official roles</span>")) {
                    correctContent = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /* Hard code fix <i> tag error */
        doc = doc.replaceAll("<i></div></i>", "<i></i></div>");

        doc = "<root>" + doc + "</root>";
        return doc;
    }

    public String getHeroDataFromHtml(String url) {
        String doc = "";

        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(url);

            String line;
            boolean correctContent = false;

            while ((line = bReader.readLine()) != null) {
                if (correctContent && line.contains("col-md-12 patch-history")) {
                    break;
                }

                if (correctContent) {
                    doc += line.trim();
                }

                if (line.contains("col-md-4 col-md-push-8")) {
                    correctContent = true;
                    doc += line.trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        doc = "<root>" + doc + "</root>";
        doc = Utils.autoCloseTag(doc, "img");
        doc = Utils.autoCloseTag(doc, "br");
        doc = doc.replace("\\\"", "\"");
        return doc;
    }

    public String getCounterHeroesFromHtml(String url) {
        String doc = "";

        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(url);

            String line;
            boolean correctContent = false;

            while ((line = bReader.readLine()) != null) {
                if (correctContent && line.contains("<!--")) {
                    break;
                }

                if (correctContent) {
                    doc += line.trim();
                }

                if (line.contains("id=\"Bad_against") || line.contains("id=\"Bad_Against")) {
                    correctContent = true;
                    doc += line.trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Normalize some special characters */
        if (doc.contains("&#39;")) {
            doc = doc.replaceAll("&#39;", "'");
        }

        doc = "<root>" + doc + "</root>";
        doc = Utils.autoCloseTag(doc, "img");
        return doc;
    }

    public void saveToDatabase() {
        RoleBLO roleBLO = new RoleBLO();
        HeroBLO heroBLO = new HeroBLO();
        SkillBLO skillBLO = new SkillBLO();
        RoleOfHeroBLO roleOfHeroBLO = new RoleOfHeroBLO();
        for (Role role : this.roleList) {
            roleBLO.add(role);
        }
        for (Hero hero : this.heroList) {
            heroBLO.add(hero);
            for (Skill skill : hero.getSkillList()) {
                skillBLO.add(skill);
            }
            for (RoleOfHero roleOfHero : hero.getRoleOfHeroList()) {
                roleOfHeroBLO.add(roleOfHero);
            }
        }
    }
}
