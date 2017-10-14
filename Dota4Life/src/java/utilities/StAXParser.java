package utilities;

import entities.Hero;
import entities.Role;
import entities.RoleOfHero;
import entities.Skill;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
            boolean skillEntry = false;
            boolean bioEntry = false;
            boolean bioContent = false;
            boolean skillNameEntry = false;
            
            String lore = null;
            String heroImg = null;

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
            skillData.put("coolDown", null);
            skillData.put("manaCost", null);
            skillData.put("scepterNote", null);
            skillData.put("linkenNote", null);
            skillData.put("bkbNote", null);
            skillData.put("mantaNote", null);
            skillData.put("silverNote", null);

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
                                // Get attribute name src in tag
                                heroImg = startElement.getAttributeByName(new QName("src")).getValue();
                                hero.setHeroImg(heroImg);
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
                            }
                            
                            if (attr.getValue().contains("abilityname")) {
                                skillNameEntry = true;
                            }
                        }
                    } // end of open tag <div>
                    
                    if (startElement.getName().toString().equals("small")) {
                        if (bioEntry) {
                            bioContent = true;
                        }
                        
                    } // end of open tag <div>
                } // end if startElement

                /* Check characters */
                if (event.isCharacters()) {
                    String str = event.asCharacters().getData();
                    
                    if (bioContent) {
                        str = str.replaceAll("\\\"", "\"");
                        if (lore == null) {
                            lore = str;
                        } else {
                            lore = "<br/>" + str;
                        }
                    }
                    
                    if (skillNameEntry) {
                        skillData.put("skillName", str);
                    }
                } // end if characters

                /* Check end element */
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    
                    if (endElement.getName().toString().equals("small")) {
                        if (bioContent) {
                            bioEntry = false;
                            bioContent = false;
                        }
                    }
                    
                    if (endElement.getName().toString().equals("div")) {
                        if (skillNameEntry) {
                            skillNameEntry = false;
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
                                    if (each.getHeroName().equals(heroName)) {

                                        /* Add hero to the right list */
                                        switch (listType) {
                                            case 1: // [Weak with Heroes] list
                                                hero.getWeakWithHeroes().add(each);
                                                break;

                                            case 2: // [Strong with Heroes] list
                                                hero.getStrongWithHeroes().add(each);
                                                break;

                                            default: // [Combo with Heroes] list
                                                hero.getComboWithHeroes().add(each);
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
                    if (str.contains("Bad against") || str.contains("Good against") || str.contains("Works well")) {
                        listType++;
                    }
                }

                /* Check start element */
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                }
            }

        } catch (XMLStreamException ex) {
//            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
System.out.println("Error on parsing hero: " + hero.getHeroName());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

        }
    }

    /* Function to parse XML document to data of Roles of many Heroes */
    public static void parseXMLRolesOfHeroes(String document, List<Hero> heroList) throws XMLStreamException {
        XMLInputFactory fact = XMLInputFactory.newInstance();
        fact.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, false);
        fact.setProperty(XMLInputFactory.IS_VALIDATING, false);

        XMLEventReader reader = null;

        try {
            InputStream is = new ByteArrayInputStream(document.getBytes("UTF-8"));
            reader = fact.createXMLEventReader(new InputStreamReader(is, "UTF-8"));

            /* Flag variables for parser */
            boolean heroEntry = false;

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
                                role = new Role();
                                role.setRoleName(roleName);

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
                            if (each.getHeroName().equals(heroName)) {
                                hero = each;
                            }
                        } // end for heroList

                        RoleOfHero roh = new RoleOfHero();
                        roh.setRoleID(role);
                        roh.setHeroID(hero);

                        role.getRoleOfHeroList().add(roh);
                        hero.getRoleOfHeroList().add(roh);

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
