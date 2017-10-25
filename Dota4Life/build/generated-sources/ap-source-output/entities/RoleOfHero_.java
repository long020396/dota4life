package entities;

import entities.Hero;
import entities.Role;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-25T20:52:28")
@StaticMetamodel(RoleOfHero.class)
public class RoleOfHero_ { 

    public static volatile SingularAttribute<RoleOfHero, Role> roleID;
    public static volatile SingularAttribute<RoleOfHero, Hero> heroID;
    public static volatile SingularAttribute<RoleOfHero, Integer> id;

}