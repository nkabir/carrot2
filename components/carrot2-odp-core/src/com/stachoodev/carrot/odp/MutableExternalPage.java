/*
 * MutableExternalPage.java Created on 2004-06-25
 */
package com.stachoodev.carrot.odp;

import java.io.*;

/**
 * Represents a single ODP external page.
 * 
 * @author stachoo
 */
public class MutableExternalPage implements ExternalPage, Serializable
{
    /** This ExtenralPage's title */
    private String title;

    /** This MutableExternalPage's description */
    private String description;

    /**
     * Creates a new empty MutableExternalPage.
     */
    public MutableExternalPage()
    {
    }

    /**
     * Returns this MutableExternalPage's <code>description</code>.
     * 
     * @return
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Sets this MutableExternalPage's <code>description</code>.
     * 
     * @param description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Returns this MutableExternalPage's <code>title</code>.
     * 
     * @return
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Sets this MutableExternalPage's <code>title</code>.
     * 
     * @param title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public boolean equals(Object obj)
    {
        if (obj == this)
        {
            return true;
        }

        if (obj == null)
        {
            return false;
        }

        if (obj.getClass() != getClass())
        {
            return false;
        }

        return title.equals(((MutableExternalPage) obj).title)
            && description.equals(((MutableExternalPage) obj).description);
    }
}