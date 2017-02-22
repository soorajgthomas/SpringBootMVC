package com.sooraj.springboot.persistence.entities.construct;

import java.io.Serializable;

/**
 * Created by SOORAJ on 11-12-2016.
 */
public interface Entity<E extends Serializable> extends Serializable,Cloneable,Comparable{

    public E getId();

    public void setId(E id);

}