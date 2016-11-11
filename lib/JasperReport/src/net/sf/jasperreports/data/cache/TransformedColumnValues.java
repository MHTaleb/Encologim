/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2016 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.jasperreports.data.cache;

import java.io.IOException;
import java.io.Serializable;

import net.sf.jasperreports.engine.JRConstants;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 */
public class TransformedColumnValues implements ColumnValues, Serializable
{

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private ColumnValues rawValues;
	private ValueTransformer transformer;
	
	public TransformedColumnValues(ColumnValues rawValues,
			ValueTransformer transformer)
	{
		this.rawValues = rawValues;
		this.transformer = transformer;
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException
	{
		out.writeUnshared(rawValues);
		out.writeObject(transformer);
	}
	
	private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException
	{
		
		this.rawValues = (ColumnValues) in.readUnshared();
		this.transformer = (ValueTransformer) in.readObject();
	}

	@Override
	public int size()
	{
		return rawValues.size();
	}

	@Override
	public ColumnValuesIterator iterator()
	{
		return new TransformedIterator();
	}

	protected class TransformedIterator implements ColumnValuesIterator
	{
		private final ColumnValuesIterator rawIterator;

		public TransformedIterator()
		{
			rawIterator = rawValues.iterator();
		}
		
		@Override
		public void moveFirst()
		{
			rawIterator.moveFirst();
		}

		@Override
		public boolean next()
		{
			return rawIterator.next();
		}

		@Override
		public Object get()
		{
			Object rawValue = rawIterator.get();
			return transformer.get(rawValue);
		}
		
	}
	
}
