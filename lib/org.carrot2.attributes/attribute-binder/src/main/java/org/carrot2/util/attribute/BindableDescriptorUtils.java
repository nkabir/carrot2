
/*
 * Carrot2 project.
 *
 * Copyright (C) 2002-2012, Dawid Weiss, Stanisław Osiński.
 * All rights reserved.
 *
 * Refer to the full license file "carrot2.LICENSE"
 * in the root folder of the repository checkout or at:
 * http://www.carrot2.org/carrot2.LICENSE
 */

package org.carrot2.util.attribute;

import com.google.common.reflect.Reflection;

/**
 * Utilities related to generated bindable descriptors.
 */
public final class BindableDescriptorUtils
{
    private BindableDescriptorUtils()
    {
        // no instances.
    }

    /**
     * Return the mapped descriptor class name for a given class name (descriptors for nested
     * classes become top-level classes).
     */
    public static String getDescriptorClassName(String className)
    {
        String packageName = Reflection.getPackageName(className);

        // Nested classes will get a separate descriptor.
        String shortClassName;
        if (packageName.isEmpty())
        {
            shortClassName = className;
        }
        else
        {
            shortClassName = className.substring(packageName.length() + 1);
        }
        shortClassName = shortClassName.replace('$', '_');

        return packageName
             + (packageName.isEmpty() ? "" : ".")
             + shortClassName
             + "Descriptor";
    }

    /**
     * @return Returns the descriptor class for a class marked with {@link Bindable}.
     * @throws IllegalArgumentException If the class is not annotated with {@link Bindable}.
     * @throws NoClassDefFoundError If the descriptor cannot be found.
     */
    @SuppressWarnings("unchecked")
    public static Class<? extends IBindableDescriptor> getDescriptorClass(Class<?> clazz)
    {
        if (clazz.getAnnotation(Bindable.class) == null)
            throw new IllegalArgumentException("Class not marked with @Bindable: "
                + clazz.getName());

        ClassLoader cl = clazz.getClassLoader();
        String descriptorClassName = getDescriptorClassName(clazz.getName());
        try
        {
            return (Class<? extends IBindableDescriptor>) 
                Class.forName(descriptorClassName, true, cl);
        }
        catch (ClassNotFoundException e)
        {
            throw new NoClassDefFoundError("Descriptor class not found: "
                + descriptorClassName);
        }
    }

    /**
     * @return Returns a descriptor instance for a class marked with {@link Bindable}.
     * @throws IllegalArgumentException If the class is not annotated with {@link Bindable}.
     * @throws NoClassDefFoundError If the descriptor cannot be found.
     * @throws RuntimeException If instantiation fails for some reason.
     */
    public static IBindableDescriptor getDescriptor(Class<?> clazz)
    {
        try
        {
            return getDescriptorClass(clazz).newInstance();
        }
        catch (InstantiationException e)
        {
            throw new RuntimeException(e);
        }
        catch (IllegalAccessException e)
        {
            throw new RuntimeException(e);
        }
    }
}