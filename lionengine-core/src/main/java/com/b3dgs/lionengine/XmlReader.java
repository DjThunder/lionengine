/*
 * Copyright (C) 2013-2020 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.b3dgs.lionengine;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;

import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Describe an XML node, which can be read.
 * <p>
 * Note: Special case for the string stored as <code>null</code> which is in fact stored as {@link #NULL}. When
 * read, the {@link #NULL} string is return if the stored string was <code>null</code>.
 * </p>
 */
public class XmlReader
{
    /** Null string (represents a string stored as <code>null</code>). */
    public static final String NULL = "null";
    /** Node error. */
    static final String ERROR_NODE = "Node not found: ";
    /** Error when reading the file. */
    static final String ERROR_READING = "An error occured while reading";
    /** Attribute error. */
    static final String ERROR_ATTRIBUTE = "The following attribute does not exist: ";

    /** Document. */
    protected final Document document;
    /** Root reference. */
    protected final Element root;

    /**
     * Create node from media.
     * 
     * @param media The XML media path (must not be <code>null</code>).
     * @throws LionEngineException If invalid argument or error when loading media.
     */
    public XmlReader(Media media)
    {
        super();

        Check.notNull(media);

        try (InputStream input = media.getInputStream())
        {
            document = DocumentFactory.createDocument(input);
            root = document.getDocumentElement();
        }
        catch (final IOException exception)
        {
            throw new LionEngineException(exception, media, ERROR_READING);
        }
    }

    /**
     * Create node.
     * 
     * @param name The node name (must not be <code>null</code>).
     * @throws LionEngineException If invalid argument or error when creating the node.
     */
    public XmlReader(String name)
    {
        super();

        Check.notNull(name);

        try
        {
            document = DocumentFactory.createDocument();
            root = document.createElement(name);
        }
        catch (final DOMException exception)
        {
            throw new LionEngineException(exception);
        }
    }

    /**
     * Internal constructor.
     * 
     * @param document The document reference (must not be <code>null</code>).
     * @param root The root reference (must not be <code>null</code>).
     * @throws LionEngineException If invalid argument.
     */
    XmlReader(Document document, Element root)
    {
        super();

        Check.notNull(document);
        Check.notNull(root);

        this.document = document;
        this.root = root;
    }

    /**
     * Read a boolean.
     * 
     * @param attribute The boolean name (must not be <code>null</code>).
     * @return The boolean value.
     * @throws LionEngineException If error when reading.
     */
    public boolean readBoolean(String attribute)
    {
        return Boolean.parseBoolean(getValue(attribute));
    }

    /**
     * Read a boolean.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The boolean name (must not be <code>null</code>).
     * @return The boolean value.
     */
    public boolean readBoolean(boolean defaultValue, String attribute)
    {
        return Boolean.parseBoolean(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read a boolean.
     * 
     * @param attribute The boolean name (must not be <code>null</code>).
     * @return The boolean value.
     * @throws LionEngineException If error when reading.
     */
    public Optional<Boolean> readBooleanOptional(String attribute)
    {
        if (hasAttribute(attribute))
        {
            return Optional.of(Boolean.valueOf(readBoolean(attribute)));
        }
        return Optional.empty();
    }

    /**
     * Read a byte.
     * 
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The byte value.
     * @throws LionEngineException If error when reading.
     */
    public byte readByte(String attribute)
    {
        return Byte.parseByte(getValue(attribute));
    }

    /**
     * Read a byte.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The byte value.
     */
    public byte readByte(byte defaultValue, String attribute)
    {
        return Byte.parseByte(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read a short.
     * 
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The short value.
     * @throws LionEngineException If error when reading.
     */
    public short readShort(String attribute)
    {
        return Short.parseShort(getValue(attribute));
    }

    /**
     * Read a short.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The short value.
     * @throws LionEngineException If invalid argument.
     */
    public short readShort(short defaultValue, String attribute)
    {
        return Short.parseShort(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read an integer.
     * 
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The integer value.
     * @throws LionEngineException If error when reading.
     */
    public int readInteger(String attribute)
    {
        return Integer.parseInt(getValue(attribute));
    }

    /**
     * Read an integer.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The integer value.
     * @throws LionEngineException If invalid argument.
     */
    public int readInteger(int defaultValue, String attribute)
    {
        return Integer.parseInt(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read an integer.
     * 
     * @param attribute The integer name (must not be <code>null</code>).
     * @return The integer value.
     * @throws LionEngineException If error when reading.
     */
    public OptionalInt readIntegerOptional(String attribute)
    {
        if (hasAttribute(attribute))
        {
            return OptionalInt.of(readInteger(attribute));
        }
        return OptionalInt.empty();
    }

    /**
     * Read a long.
     * 
     * @param attribute The float name (must not be <code>null</code>).
     * @return The long value.
     * @throws LionEngineException If error when reading.
     */
    public long readLong(String attribute)
    {
        return Long.parseLong(getValue(attribute));
    }

    /**
     * Read a long.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The float name (must not be <code>null</code>).
     * @return The long value.
     * @throws LionEngineException If invalid argument.
     */
    public long readLong(long defaultValue, String attribute)
    {
        return Long.parseLong(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read a float.
     * 
     * @param attribute The float name (must not be <code>null</code>).
     * @return The float value.
     * @throws LionEngineException If error when reading.
     */
    public float readFloat(String attribute)
    {
        return Float.parseFloat(getValue(attribute));
    }

    /**
     * Read a float.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The float name (must not be <code>null</code>).
     * @return The float value.
     * @throws LionEngineException If invalid argument.
     */
    public float readFloat(float defaultValue, String attribute)
    {
        return Float.parseFloat(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read a double.
     * 
     * @param attribute The double name (must not be <code>null</code>).
     * @return The double value.
     * @throws LionEngineException If error when reading.
     */
    public double readDouble(String attribute)
    {
        return Double.parseDouble(getValue(attribute));
    }

    /**
     * Read a double.
     * 
     * @param defaultValue The value returned if attribute not found.
     * @param attribute The double name (must not be <code>null</code>).
     * @return The double value.
     * @throws LionEngineException If invalid argument.
     */
    public double readDouble(double defaultValue, String attribute)
    {
        return Double.parseDouble(getValue(String.valueOf(defaultValue), attribute));
    }

    /**
     * Read a double.
     * 
     * @param attribute The double name (must not be <code>null</code>).
     * @return The double value.
     * @throws LionEngineException If error when reading.
     */
    public OptionalDouble readDoubleOptional(String attribute)
    {
        if (hasAttribute(attribute))
        {
            return OptionalDouble.of(readDouble(attribute));
        }
        return OptionalDouble.empty();
    }

    /**
     * Read a string. If the read string is equal to {@link #NULL}, <code>null</code> will be returned instead.
     * 
     * @param attribute The string name (must not be <code>null</code>).
     * @return The string value.
     * @throws LionEngineException If error when reading.
     */
    public String readString(String attribute)
    {
        final String value = getValue(attribute);
        if (NULL.equals(value))
        {
            return null;
        }
        return value;
    }

    /**
     * Read a string. If the read string is equal to {@link #NULL}, <code>null</code> will be returned instead.
     * 
     * @param defaultValue The value returned if attribute not found (can be <code>null</code>).
     * @param attribute The string name (must not be <code>null</code>).
     * @return The string value.
     * @throws LionEngineException If invalid arguments.
     */
    public String readString(String defaultValue, String attribute)
    {
        final String value = getValue(defaultValue, attribute);
        if (NULL.equals(value))
        {
            return null;
        }
        return value;
    }

    /**
     * Read a string.
     * 
     * @param attribute The string name (must not be <code>null</code>).
     * @return The string value.
     * @throws LionEngineException If error when reading.
     */
    public Optional<String> readStringOptional(String attribute)
    {
        if (hasAttribute(attribute))
        {
            return Optional.ofNullable(readString(attribute));
        }
        return Optional.empty();
    }

    /**
     * Get the name of the current node.
     * 
     * @return The node name.
     */
    public String getNodeName()
    {
        return root.getTagName();
    }

    /**
     * Return the text inside the node.
     * 
     * @return The text.
     */
    public String getText()
    {
        return root.getTextContent();
    }

    /**
     * Get all attributes.
     * 
     * @return The attributes map reference.
     */
    public Map<String, String> getAttributes()
    {
        final NamedNodeMap map = root.getAttributes();
        final int length = map.getLength();
        final Map<String, String> attributes = new HashMap<>(length);
        for (int i = 0; i < length; i++)
        {
            final Node node = map.item(i);
            attributes.put(node.getNodeName(), node.getNodeValue());
        }
        return attributes;
    }

    /**
     * Check if node has the following attribute.
     * 
     * @param attribute The attribute name (can be <code>null</code>).
     * @return <code>true</code> if attribute exists, <code>false</code> else.
     */
    public boolean hasAttribute(String attribute)
    {
        if (attribute == null)
        {
            return false;
        }
        return root.hasAttribute(attribute);
    }

    /**
     * Check if node has the following child.
     * 
     * @param child The child name (can be <code>null</code>).
     * @return <code>true</code> if child exists, <code>false</code> else.
     */
    public boolean hasChild(String child)
    {
        final NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            final Node node = list.item(i);
            if (node.getNodeName().equals(child))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Get a child node from its name.
     * 
     * @param name The child name (must not be <code>null</code>).
     * @return The child node reference.
     * @throws LionEngineException If no node is found at this child name.
     */
    public XmlReader getChild(String name)
    {
        Check.notNull(name);

        final NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            final Node node = list.item(i);
            if (node instanceof Element && node.getNodeName().equals(name))
            {
                return new XmlReader(document, (Element) node);
            }
        }
        throw new LionEngineException(ERROR_NODE + name);
    }

    /**
     * Get a child node from its name.
     * 
     * @param name The child name.
     * @return The child node reference.
     */
    public Optional<XmlReader> getChildOptional(String name)
    {
        final NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            final Node node = list.item(i);
            if (node instanceof Element && node.getNodeName().equals(name))
            {
                return Optional.of(new XmlReader(document, (Element) node));
            }
        }
        return Optional.empty();
    }

    /**
     * Get the list of all children with this name.
     * 
     * @param name The children name (must not be <code>null</code>).
     * @return The children list.
     * @throws LionEngineException If invalid argument.
     */
    public Collection<? extends XmlReader> getChildren(String name)
    {
        Check.notNull(name);

        final Collection<XmlReader> nodes = new ArrayList<>(1);
        final NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            final Node node = list.item(i);
            if (name.equals(node.getNodeName()))
            {
                nodes.add(new XmlReader(document, (Element) node));
            }
        }
        return nodes;
    }

    /**
     * Get list of all children.
     * 
     * @return The children list.
     */
    public Collection<? extends XmlReader> getChildren()
    {
        final Collection<XmlReader> nodes = new ArrayList<>(1);
        final NodeList list = root.getChildNodes();
        for (int i = 0; i < list.getLength(); i++)
        {
            final Node node = list.item(i);
            if (node instanceof Element)
            {
                nodes.add(new XmlReader(document, (Element) node));
            }
        }
        return nodes;
    }

    /**
     * Get the original element.
     * 
     * @return The jdom element.
     */
    Element getElement()
    {
        return root;
    }

    /**
     * Get the attribute value.
     * 
     * @param attribute The attribute name (must not be <code>null</code>).
     * @return The attribute value.
     * @throws LionEngineException If attribute is not valid or does not exist.
     */
    private String getValue(String attribute)
    {
        Check.notNull(attribute);

        if (root.hasAttribute(attribute))
        {
            return root.getAttribute(attribute);
        }
        throw new LionEngineException(ERROR_ATTRIBUTE + attribute);
    }

    /**
     * Get the attribute value.
     * 
     * @param defaultValue The value returned if attribute does not exist (can be <code>null</code>).
     * @param attribute The attribute name (must not be <code>null</code>).
     * @return The attribute value.
     * @throws LionEngineException If attribute is not valid or does not exist.
     */
    private String getValue(String defaultValue, String attribute)
    {
        Check.notNull(attribute);

        if (root.hasAttribute(attribute))
        {
            return root.getAttribute(attribute);
        }
        return defaultValue;
    }
}
