package org.usfirst.ihs1318.telemetry;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataValue {
	
	private String name;
	private DataType dataType;
	private List<RawData> values;
	private String publishedValue="No data";
	private boolean currentBoolean;
	private double currentDouble;
	private String currentString ="";

	private Map<String, DataType> dataTypes = new HashMap<String, DataType>();
	
	
	public DataValue() {
		String[] dtArray = FIELD_TYPES_2014.split("\n");
		for (String dt : dtArray) {
			String[] kv = dt.split("=");
			if ("Boolean".equalsIgnoreCase(kv[1].trim())) {
				dataTypes.put(kv[0].trim(), DataType.BOOLEAN);
			}
			else if ("Double".equalsIgnoreCase(kv[1].trim())) {
				dataTypes.put(kv[0].trim(), DataType.DOUBLE);
			}
			else {
				dataTypes.put(kv[0].trim(), DataType.STRING);
			}
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<RawData> getValues() {
		if (values==null) values = new ArrayList<RawData>();
		return values;
	}
	public void setValues(List<RawData> values) {
		this.values = values;
	}
	
	public void addRawData(RawData rd) {
		setDataType(dataTypes.get(rd.getName()));
		if (getDataType()==null) {
			System.out.println("No data type for "+rd.getName());
			return;
		}
		getValues().add(rd); 
		switch (getDataType()) {
		case BOOLEAN:
				if (!currentBoolean && "true".equalsIgnoreCase(rd.getRawData())) {
					currentBoolean = true;
				}
				break;
		case DOUBLE:
			// maintain running average
			// http://en.wikipedia.org/wiki/Standard_deviation#Rapid_calculation_methods
			try {
			double d = Double.parseDouble(rd.getRawData());
			currentDouble += (d - currentDouble)/getValues().size();
			} catch (NumberFormatException nfe) {
				System.out.println(String.format("%s=%s",rd.getName(),rd.getRawData()));
				throw nfe;
			}
			break;
		case STRING:
			currentString = rd.getRawData();
			break;
		} 
	}
	public DataType getDataType() {
		return dataType;
	}
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}
	public String getPublishedValue() {
		if (getDataType()==null) {
			throw new RuntimeException("Unknown datatype for "+getName());
		}
		switch(getDataType()) {
		case BOOLEAN:
			return ""+currentBoolean;
		case STRING:
			return currentString;
		case DOUBLE:
			return ""+currentDouble;
		}
		return publishedValue;
	}
	public void setPublishedValue(String publishedValue) {
		this.publishedValue = publishedValue;
	}
	
	public String toString() {
		return getDataType()+","+getName()+","+getValues().size()+","+getPublishedValue();
		
	}
	
	public static final String FIELD_TYPES_2014 = 	
			"c.ec=Boolean\n" + 
			"c.rc=Boolean\n" + 
			"cl.bp=Boolean\n" + 
			"cl.css=Boolean\n" + 
			"cl.dss=Boolean\n" + 
			"cl.ms=Double\n" + 
			"dt.le=Double\n" + 
			"dt.lev=Double\n" + 
			"dt.lps=Double\n" + 
			"dt.lsp=Double\n" + 
			"dt.re=Double\n" + 
			"dt.rev=Double\n" + 
			"dt.rps=Double\n" + 
			"dt.rsp=Double\n" + 
			"i.cb=Boolean\n" + 
			"i.cmi=Boolean\n" + 
			"i.cmo=Boolean\n" + 
			"i.eb=Boolean\n" + 
			"i.ec=Boolean\n" + 
			"i.gf=Boolean\n" + 
			"i.jx=Double\n" + 
			"i.jy=Double\n" + 
			"i.rc=Boolean\n" + 
			"i.ss=Double\n" + 
			"ls.d=Boolean\n" + 
			"ps.av=Double\n" + 
			"ps.s=Boolean\n" + 
			"ps.t=Double\n" + 
			"r.s=String\n" + 
			"s.ae=Boolean\n" + 
			"s.es=Boolean\n" + 
			"s.rs=Boolean\n" + 
			"s.se=Boolean\n" + 
			"spid.dw=Double\n" + 
			"spid.nto=Boolean\n" + 
			"spid.up=Double\n" + 
			"t.m=String\n" + 
			"at.swt=Double\n" +
			"at.d=Double\n" +
			"at.ad=Double\n" + 
			"at.as1=Double\n" +
			"at.ci=Double\n" +
			"at.cii=Double\n" + 
			"at.s=Double\n" +
			"at.swti=Double\n" +
			"at.di=Double\n" +
			"at.adi=Double\n" + 
			"at.aci=Double\n" +
			"at.as1i=Double\n" +
			"at.cii=Double\n" +
			"at.acii=Double\n" + 
			"at.si=Double\n" + 
			"at.np=Double\n" +
			"at.npi=Double\n" +
			"cl.cw=Double\n" +
			"cl.cwi=Double\n" +
			"cl.sw=Double\n" +
			"cl.swi=Double\n"
	         ;		

			public static final String FIELD_TYPES_2013 = 
			"dt.re=Double\n"
					+ "dt.le=Double\n"
					+ "dt.rsp=Double\n"
					+ "dt.ldp=Double\n"
					+ "dt.rps=Double\n"
					+ "dt.lps=Double\n"
					+ "l.sl=Boolean\n"
					+ "lms.s=Boolean\n"
					+ "s.sp==Double\n"
					+ "s.aes=Double\n"
					+ "s.aest=Double\n"
					+ "s.m=Double\n"
					+ "s.hf=Boolean\n"
					+ "s.sf=Boolean\n"
					+ "s.c=String\n"
					+ "so.sbrun=Boolean\n"
					+ "so.sbu=Boolean\n"
					+ "so.lbu=Boolean\n"
					+ "so.fbe=Boolean\n"
					+ "so.su=Boolean\n"
					+ "so.lu=Boolean\n"
					+ "so.fe=Boolean\n"
					+ "i.ljs=Double\n"
					+ "i.rjs=Double\n"
					+ "i.jx=Double\n"
					+ "i.jy=Double\n"
					+ "i.ld=Boolean\n"
					+ "i.lu=Boolean\n"
					+ "i.sd=Boolean\n"
					+ "i.su=Boolean\n"
					+ "i.ssu=Boolean\n"
					+ "i.ssd=Boolean\n"
					+"i.sf=Boolean\n"
					+"spid.nto=Boolean\n"
					+"spid.vsp=Double\n"
					+"r.s=String\n";

	public static final String FIELD_TYPES_2011 = "Air=Boolean\n" + 
			"Tube=String\n" + 
			"armCV=Double\n" + 
			" armD=Double\n" + 
			" armEncR=Double\n" + 
			" armEncV=Double\n" + 
			" armH=String\n" + 
			" armV=Double\n" + 
			" bot=Boolean\n" + 
			" cFlip=String\n" + 
			" claw=Boolean\n" + 
			" gyro=Boolean\n" + 
			" jX=Double\n" + 
			" jY=Double\n" + 
			" jrY=Double\n" + 
			" lineC=Boolean\n" + 
			" lineD=String\n" + 
			" lineL=Boolean\n" + 
			" lineR=Boolean\n" + 
			" omega=Double\n" + 
			" theta=Double\n" + 
			" wLF=Double\n" + 
			" wLR=Double\n" + 
			" wRF=Double\n" + 
			" wRR=Double\n" + 
			" weLF=Double\n" + 
			" weLR=Double\n" + 
			" weRF=Double\n" + 
			" weRR=Double\n" + 
			" wrist=String\n" + 
			" wristD=Boolean\n" + 
			" wristP =Boolean";
}
