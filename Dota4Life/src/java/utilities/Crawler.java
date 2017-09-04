package utilities;

import entities.Attribute;
import entities.Hero;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Crawler {

    private List<Hero> heroList;
    private Map<String, String> linksOfHeroes;
    private Map<String, String> linksOfCounterHeroes;

    public Crawler() {
        this.heroList = new ArrayList<Hero>();
        this.linksOfHeroes = new HashMap<String, String>();
        this.linksOfCounterHeroes = new HashMap<String, String>();
    }

    public void crawlData() {
        getListOfHero();
        crawlRole();
        crawlHero();
    }

    public void getListOfHero() {
        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(ConstantManager.URL_DOTA2WIKI_HOME);

            boolean correctContent = false;
            String line;
            String attributeName = null;
            String heroName = null;
            String heroLink = null;
            Attribute attribute = null;

            while ((line = bReader.readLine()) != null) {

                if (correctContent) {
                    if (line.contains("<th")) {
                        String aTag = Utils.getHtmlOpenTag(line, "a");
                        attributeName = Utils.getHtmlAttributeValue(aTag, "title");
                        attribute = new Attribute();
                        attribute.setAttributeName(attributeName);
                    }

                    if (line.contains("<td")) {
                        String[] listTags = line.split(">, <");
                        for (String eachTag : listTags) {
                            String aTag = Utils.getHtmlOpenTag(eachTag, "a");
                            heroName = Utils.getHtmlAttributeValue(aTag, "title");
                            heroLink = ConstantManager.URL_DOTA2WIKI + Utils.getHtmlAttributeValue(aTag, "href");

                            /* Set new Hero */
                            Hero hero = new Hero();
                            hero.setHeroName(heroName);
                            hero.setAttributeID(attribute);

                            /* Add Hero to List */
                            this.heroList.add(hero);

                            /* Add link to HashMap */
                            this.linksOfHeroes.put(heroName, heroLink);
                            this.linksOfCounterHeroes.put(heroName, heroLink + "/Counters");

                        }
                    }
                }

                if (line.contains("<table style=\"text-align:center")) {
                    correctContent = true;
                }

                if (correctContent && line.contains("</table")) {
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
            StAXParser.parseXMLRolesOfHeroes(document, this.heroList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void crawlHero() {
        int count = 0;
        for (Map.Entry<String, String> entry : this.linksOfHeroes.entrySet()) {
            String document = getSkillDataFromHtml(entry.getValue());
            Hero hero = null;
            int index = -1;
            for (Hero each : this.heroList) {
                index++;
                if (each.getHeroName().equals(entry.getKey())) {
                    hero = each;
                }
            } // end for heroList
            try {
                /* parse data by StAX */
                StAXParser.parseXMLHeroData(document, hero);
                
                /* Set back hero to ArrayList */
                this.heroList.set(index, hero);
                
                /* Sleep for 2s to prevent DDOS protection */
                count++;
                if (count % 40 == 0) {
                    Thread.sleep(2000);
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(Crawler.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } // end for linksOfHeroes.entrySet()
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
                    doc += line;
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

    public String getSkillDataFromHtml(String url) {
        String doc = "";
        try {
            BufferedReader bReader = BufferedReaderProvider.getBufferedReader(url);

            String line;
            boolean correctContent = false;

            while ((line = bReader.readLine()) != null) {
                if (line.contains("<span class=\"mw-headline\" id=\"Talents\">Talents</span>")) {
                    break;
                }

                if (correctContent) {
                    doc += line;
                }

                if (line.contains("<table class=\"infobox\" style=\"text-align:center; font-size:88%; line-height:1.5em; white-space:nowrap;\">")) {
                    correctContent = true;
                    doc += line;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        doc = "<root>" + doc + "</root>";
        return doc;
    }
}
