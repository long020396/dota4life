package entities;

import entities.RoleOfHero;
import entities.Skill;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-25T20:52:28")
@StaticMetamodel(Hero.class)
public class Hero_ { 

    public static volatile SingularAttribute<Hero, String> badAgainstIDs;
    public static volatile SingularAttribute<Hero, String> img;
    public static volatile SingularAttribute<Hero, String> goodAgainstIDs;
    public static volatile SingularAttribute<Hero, String> lore;
    public static volatile ListAttribute<Hero, Skill> skillList;
    public static volatile ListAttribute<Hero, RoleOfHero> roleOfHeroList;
    public static volatile SingularAttribute<Hero, String> oldName;
    public static volatile SingularAttribute<Hero, String> name;
    public static volatile SingularAttribute<Hero, Integer> id;
    public static volatile SingularAttribute<Hero, String> attribute;

}