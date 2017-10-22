package utilities;

import entities.Hero;
import entities.Role;
import entities.RoleOfHero;
import entities.Skill;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StAXParser {

    /* Function to parse XML document to data of Hero */
    public static void parseXMLHeroData(String document, Hero hero) {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            /* Flag variables for parser */
            boolean strongTag = false;
            boolean tdTag = false;
            boolean firstTdTag = true;
            boolean skillEntry = false;
            boolean bioEntry = false;
            boolean bioContent = false;
            boolean skillNameEntry = false;
            boolean abilityOrType = false;
            boolean abilityEntry = false;
            boolean damageTypeEntry = false;
            boolean descriptionEntry = false;
            boolean coolDownEntry = false;
            boolean manaCostEntry = false;
            boolean durationEntry = false;
            boolean stunDurationEntry = false;
            boolean silenceDurationEntry = false;
            boolean damageEntry = false;

            String lore = null;

            int skillOrder = 1;

            /* HashMap for String for better performance to set null */
            HashMap<String, String> skillData = new HashMap<String, String>();
            skillData.put("skillName", null);
            skillData.put("skillImg", null);
            skillData.put("ability", null);
            skillData.put("affect", null);
            skillData.put("damageType", null);
            skillData.put("description", null);
            skillData.put("damage", null);
            skillData.put("duration", null);
            skillData.put("stunDuration", null);
            skillData.put("silenceDuration", null);
            skillData.put("coolDown", null);
            skillData.put("manaCost", null);

            /* Entity */
            Skill skill = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                /* Check start element */
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().toString().equals("img")) {
                        Attribute attr = startElement.getAttributeByName(new QName("class"));
                        if (attr != null) {
                            if (attr.getValue().contains("hero_portrait")) {
                                /* Set heroImg */
                                hero.setImg(startElement.getAttributeByName(new QName("src")).getValue());
                            } else if (attr.getValue().contains("ability_portrait")) {
                                /* Set skillImg */
                                skillData.put("skillImg", startElement.getAttributeByName(new QName("src")).getValue());
                            }
                        }
                    } // end of open tag <img>

                    if (startElement.getName().toString().equals("div")) {
                        Attribute attr = startElement.getAttributeByName(new QName("class"));
                        if (attr != null) {

                            if (attr.getValue().contains("hero-bio")) {
                                bioEntry = true;
                            }

                            if (attr.getValue().contains("ability panel panel-default")) {
                                skillEntry = true;
                                skill = new Skill();
                                skill.setId(0);
                                skill.setHeroID(hero);
                                skill.setSkillOrder(skillOrder);
                            }

                            if (attr.getValue().contains("abilityname")) {
                                skillNameEntry = true;
                            }

                            if (attr.getValue().contains("col-md-10")) {
                                descriptionEntry = true;
                            }

                        }
                    } // end of open tag <div>

                    if (startElement.getName().toString().equals("small")) {
                        if (bioEntry) {
                            bioContent = true;
                        }
                    } // end of open tag <small>

                    if (startElement.getName().toString().equals("span")) {
                        Attribute attr = startElement.getAttributeByName(new QName("class"));
                        if (attr != null) {

                            if (attr.getValue().contains("col-md-6")) {
                                if (skillData.get("skillName") == null) {
                                    skillEntry = false;
                                    skillNameEntry = false;
                                } else {
                                    abilityOrType = true;
                                }
                            }
                        }
                    } // end of open tag <span>

                    if (startElement.getName().toString().equals("strong")) {
                        strongTag = true;
                    } // end of open tag <span>

                    if (startElement.getName().toString().equals("br")) {
                        if (descriptionEntry) {
                            skillData.put("description", skillData.get("description") + "<br/>");
                        }
                    } // end of open tag <br>

                    if (startElement.getName().toString().equals("tr")) {
                        if (skillEntry) {
                            firstTdTag = true;
                        }
                    } // end of open tag <tr>

                    if (startElement.getName().toString().equals("td")) {
                        if (skillEntry) {
                            tdTag = true;
                        }
                    } // end of open tag <td>

                } // end if startElement

                /* Check characters */
                if (event.isCharacters()) {
                    String str = event.asCharacters().getData();

                    /* Get lore */
                    if (bioContent) {
                        str = str.replaceAll("\\\"", "\"");
                        if (lore == null) {
                            lore = str;
                        } else {
                            lore = lore + "<br/>" + str;
                        }
                    }

                    /* Get skillName */
                    if (skillNameEntry) {
                        if (str.equals("Morph") || str.equals("Spring Early")) {
                            skillNameEntry = false;
                            skillEntry = false;
                        } else {
                            skillData.put("skillName", str);
                        }
                    }

                    /* Get ability or damageType */
                    if (abilityOrType) {
                        if (strongTag) {
                            if (str.contains("Ability")) {
                                abilityEntry = true;
                            } else if (str.contains("Damage")) {
                                damageTypeEntry = true;
                            }
                        } else {
                            if (abilityEntry) {
                                skillData.put("ability", str);
                                abilityEntry = false;
                            }
                            if (damageTypeEntry) {
                                skillData.put("damageType", str);
                                damageTypeEntry = false;
                            }
                        }
                    }

                    /* Get skillDescription */
                    if (descriptionEntry) {
                        if (skillData.get("description") == null) {
                            skillData.put("description", str);
                        } else {
                            skillData.put("description", skillData.get("description") + str);
                        }
                    }

                    /* Get skillData */
                    if (tdTag) {
                        if (firstTdTag) {
                            if (str.equals("COOLDOWN:")) {
                                coolDownEntry = true;
                            } else if (str.equals("MANA COST:")) {
                                manaCostEntry = true;
                            } else if (str.equals("DURATION:")) {
                                durationEntry = true;
                            } else if (str.equals("STUN DURATION:")) {
                                stunDurationEntry = true;
                            } else if (str.equals("SILENCE DURATION:")) {
                                silenceDurationEntry = true;
                            } else if (str.equals("DAMAGE:")) {
                                damageEntry = true;
                            }
                        } else {
                            if (coolDownEntry) {
                                skillData.put("coolDown", str);
                                coolDownEntry = false;
                            } else if (manaCostEntry) {
                                skillData.put("manaCost", str);
                                manaCostEntry = false;
                            } else if (durationEntry) {
                                skillData.put("duration", str);
                                durationEntry = false;
                            } else if (stunDurationEntry) {
                                skillData.put("stunDuration", str);
                                stunDurationEntry = false;
                            } else if (silenceDurationEntry) {
                                skillData.put("silenceDuration", str);
                                silenceDurationEntry = false;
                            } else if (damageEntry) {
                                skillData.put("damage", str);
                                damageEntry = false;
                            }
                        }
                    }
                } // end if characters

                /* Check end element */
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();

                    if (endElement.getName().toString().equals("small")) {
                        if (bioContent) {
                            bioEntry = false;
                            bioContent = false;
                            hero.setLore(lore);
                        }
                    }

                    if (endElement.getName().toString().equals("div")) {
                        if (skillNameEntry) {
                            skillNameEntry = false;
                        }

                        if (descriptionEntry) {
                            descriptionEntry = false;
                        }
                    }

                    if (endElement.getName().toString().equals("strong")) {
                        strongTag = false;
                    }

                    if (endElement.getName().toString().equals("span")) {
                        if (abilityOrType) {
                            abilityOrType = false;
                        }
                    }

                    if (endElement.getName().toString().equals("table")) {
                        if (skillEntry) {
                            skillEntry = false;

                            /* Set skillData to Skill entitiy*/
                            skill.setName(skillData.get("skillName"));
                            skill.setImg(skillData.get("skillImg"));
                            skill.setAbility(skillData.get("ability"));
                            skill.setDamageType(skillData.get("damageType"));
                            skill.setDescription(skillData.get("description"));
                            skill.setDamage(skillData.get("damage"));
                            skill.setDuration(skillData.get("duration"));
                            skill.setStunDuration(skillData.get("stunDuration"));
                            skill.setSilenceDuration(skillData.get("silenceDuration"));
                            skill.setCoolDown(skillData.get("coolDown"));
                            skill.setMana(skillData.get("manaCost"));

                            if (hero.getSkillList() == null) {
                                hero.setSkillList(new ArrayList<Skill>());
                            }
                            hero.getSkillList().add(skill);
                            skillOrder++;

                            Utils.setAllToNull(skillData);
                        }
                    }

                    if (endElement.getName().toString().equals("td")) {
                        if (skillEntry) {
                            tdTag = false;
                            firstTdTag = false;
                        }
                    }

                } // end if endElement

            } // end while 
        } catch (XMLStreamException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Function to parse XML document to data of Counter Hero */
    public static void parseXMLCounterHeroData(String document, List<Hero> heroList, Hero hero) {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            /* Flag variables for parser */
            boolean heroEntry = false;

            /*
                listType is number stands for list Counter Heroes
                    1 for [Weak with Heroes] list
                    2 for [Strong with Heroes] list
                    3 for [Combo with Heroes] list
             */
            int listType = 0;

            /* Data by text */
            String heroName = null;

            /* Entity */
            Hero tmpHero = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                /* Check start element */
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().toString().equals("div")) {
                        Attribute attr = startElement.getAttributeByName(new QName("style"));
                        if (attr != null) {
                            if (attr.getValue().contains("margin-bottom:5px; box-shadow:0px 0px 2px 4px")) {
                                heroEntry = true;
                            }
                        }
                    } // end of start tag <div>

                    if (startElement.getName().toString().equals("a")) {
                        if (heroEntry) {
                            Attribute attr = startElement.getAttributeByName(new QName("title"));
                            if (attr != null) {
                                heroName = attr.getValue();

                                for (Hero each : heroList) {
                                    if (each.getName().equals(heroName)) {

                                        /* Add hero to the right list */
                                        switch (listType) {
                                            case 1: // [Weak with Heroes] list
                                                if (hero.getBadAgainstIDs() == null) {
                                                    hero.setBadAgainstIDs(each.getId() + "");
                                                } else {
                                                    hero.setBadAgainstIDs(hero.getBadAgainstIDs() + ";" + each.getId());
                                                }
                                                break;

                                            case 2: // [Strong with Heroes] list
                                                if (hero.getGoodAgainstIDs() == null) {
                                                    hero.setGoodAgainstIDs(each.getId() + "");
                                                } else {
                                                    hero.setGoodAgainstIDs(hero.getGoodAgainstIDs() + ";" + each.getId());
                                                }
                                                break;

                                            default: // [Combo with Heroes] list
//                                                if (hero.getComboWithHeroes() == null) {
//                                                    hero.setComboWithHeroes(new ArrayList<Hero>());
//                                                }
//                                                hero.getComboWithHeroes().add(each);
                                        }
                                        break;
                                    }
                                } // end hero search
                            } // end if attr != null
                            heroEntry = false;
                        }
                    } // end of start tag <a>
                }

                /* Check characters */
                if (event.isCharacters()) {
                    String str = event.asCharacters().getData();
                    str = str.toUpperCase();
                    if (str.contains("BAD AGAINST") || str.contains("GOOD AGAINST") || str.contains("WORKS WELL")) {
                        listType++;
                    }
                }

                /* Check start element */
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                }
            }

        } catch (XMLStreamException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    /* Function to parse XML document to data of Roles of many Heroes */
    public static void parseXMLRolesOfHeroes(String document, List<Hero> heroList, List<Role> roleList, boolean roleListExisted) throws XMLStreamException {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            /* Flag variables for parser */
            boolean heroEntry = false;

            int index = 0;

            /* Data by text */
            String roleName = null;
            String heroName = null;

            /* Entity */
            Role role = null;
            Hero hero = null;

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();

                /* Check start element */
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();

                    if (startElement.getName().toString().equals("span")) {
                        Attribute attr = startElement.getAttributeByName(new QName("class"));
                        if (attr != null) {
                            if (attr.getValue().equals("mw-headline")) {

                                /* Get id value of span tag */
                                roleName = startElement.getAttributeByName(new QName("id")).getValue().toString();
                                if (roleListExisted) {
                                    index = 0;
                                    for (Role mainRole : roleList) {
                                        if (mainRole.getName().equals(roleName)) {
                                            role = mainRole;
                                            break;
                                        }
                                        index++;
                                    }
                                } else {
                                    role = new Role();
                                    role.setName(roleName);
                                    roleList.add(role);
                                }

                            } // end if attr.getValue()
                        } // end if attr != null
                    } // end of start tag <span>

                    if (startElement.getName().toString().equals("div")) {
                        Attribute attr = startElement.getAttributeByName(new QName("class"));
                        if (attr != null) {
                            if (attr.getValue().equals("heroentrytext")) {
                                heroEntry = true;
                            } // end if attr.getValue()
                        } // end if attr != null
                    } // end of start tag <div>
                } // end if startElement

                /* Check characters */
                if (event.isCharacters()) {
                    if (heroEntry) {
                        heroName = event.asCharacters().getData();
                        for (Hero each : heroList) {
                            if (each.getName().equals(heroName)) {
                                hero = each;
                            }
                        } // end for heroList

                        RoleOfHero roh = new RoleOfHero();
                        roh.setRoleID(role);
                        roh.setHeroID(hero);

                        if (roleListExisted) {
                            if (role.getRoleOfHeroList() == null) {
                                role.setRoleOfHeroList(new ArrayList<RoleOfHero>());
                            }
                            role.getRoleOfHeroList().add(roh);
                            roleList.set(index, role);
                            if (hero.getRoleOfHeroList() == null) {
                                hero.setRoleOfHeroList(new ArrayList<RoleOfHero>());
                            }
                            hero.getRoleOfHeroList().add(roh);
                        }

                        heroEntry = false;
                    } // end if heroEntry
                } // end if characters
            } // end while
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            reader.close();
        }
    }
}
