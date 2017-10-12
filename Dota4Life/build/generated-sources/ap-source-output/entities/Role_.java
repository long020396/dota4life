package entities;

import entities.RoleOfHero;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-10-11T10:01:52")
@StaticMetamodel(Role.class)
public class Role_ { 

    public static volatile ListAttribute<Role, RoleOfHero> roleOfHeroList;
    public static volatile SingularAttribute<Role, String> roleName;
    public static volatile SingularAttribute<Role, String> description;
    public static volatile SingularAttribute<Role, Integer> id;

}