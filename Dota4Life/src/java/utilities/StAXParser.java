package utilities;

import entities.Hero;
import entities.Role;
import entities.RoleOfHero;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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
            
            boolean skillEntry = false;
            
        } catch (XMLStreamException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /* Function to parse XML document to data of Counter Hero */
    public static void parseXMLCounterHeroData(String document, Hero hero) {

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

            boolean heroEntry = false;
            String roleName = null;
            String heroName = null;
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
                    } // end if attr.getName() == span
                    
                    if (startElement.getName().toString().equals("div")) {
                        Attribute attr = startElement.getAttributeByName(new QName("class"));
                        if (attr != null) {
                            if (attr.getValue().equals("heroentrytext")) {
                                heroEntry = true;
                            } // end if attr.getValue()
                        } // end if attr != null
                    } // end if attr.getName() == div
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
                        
                        List<RoleOfHero> rohList = hero.getRoleOfHeroList();
                        rohList.add(roh);
                        hero.setRoleOfHeroList(rohList);
                        
                        heroEntry = false;
                    } // end if heroEntry
                } // end if characters
                
                /* Check end element */
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    /* Not neccessary */
                }
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(StAXParser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            reader.close();
        }
    }
}
