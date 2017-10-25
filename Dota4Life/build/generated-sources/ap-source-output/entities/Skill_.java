package entities;

import entities.Hero;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-25T20:52:28")
@StaticMetamodel(Skill.class)
public class Skill_ { 

    public static volatile SingularAttribute<Skill, String> damage;
    public static volatile SingularAttribute<Skill, String> img;
    public static volatile SingularAttribute<Skill, String> affect;
    public static volatile SingularAttribute<Skill, String> description;
    public static volatile SingularAttribute<Skill, Hero> heroID;
    public static volatile SingularAttribute<Skill, String> stunDuration;
    public static volatile SingularAttribute<Skill, String> coolDown;
    public static volatile SingularAttribute<Skill, String> duration;
    public static volatile SingularAttribute<Skill, Integer> skillOrder;
    public static volatile SingularAttribute<Skill, String> mana;
    public static volatile SingularAttribute<Skill, String> name;
    public static volatile SingularAttribute<Skill, String> silenceDuration;
    public static volatile SingularAttribute<Skill, Integer> id;
    public static volatile SingularAttribute<Skill, String> ability;
    public static volatile SingularAttribute<Skill, String> damageType;

}