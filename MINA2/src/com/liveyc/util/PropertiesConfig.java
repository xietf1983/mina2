/*
 * Created on 2006-5-26 Created by yangj
 * 
 * 
 */
package com.liveyc.util;

import com.ice.exception.NanwangException;

/**
 * @author yangj
 * 
 */
public interface PropertiesConfig {
	public void fromXmlStr(String xml) throws Exception;

	public void saveProp() throws Exception;

	public String toXmlStr(boolean isRuntime) throws Exception;

	public void reloadProps() throws Exception;

	public void setPropRunTime(String name, String value) throws Exception;
}
