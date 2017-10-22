package entities;

import entities.Hero;
import entities.RoleOfHero;
import entities.Skill;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-21T09:11:33")
@StaticMetamodel(Hero.class)
public class Hero_ { 

    public static volatile SingularAttribute<Hero, String> heroOldName;
    public static volatile SingularAttribute<Hero, String> lore;
    public static volatile ListAttribute<Hero, Skill> skillList;
    public static volatile ListAttribute<Hero, RoleOfHero> roleOfHeroList;
    public static volatile SingularAttribute<Hero, Hero> strongWithHeroID;
    public static volatile ListAttribute<Hero, Hero> comboWithHeroes;
    public static volatile ListAttribute<Hero, Hero> weakWithHeroes;
    public static volatile SingularAttribute<Hero, String> heroImg;
    public static volatile SingularAttribute<Hero, Hero> weakWithHeroID;
    public static volatile SingularAttribute<Hero, Integer> id;
    public static volatile SingularAttribute<Hero, String> attribute;
    public static volatile SingularAttribute<Hero, Hero> comboWithHeroID;
    public static volatile SingularAttribute<Hero, String> heroName;
    public static volatile ListAttribute<Hero, Hero> strongWithHeroes;

}