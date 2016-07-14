package com.ice;

import org.apache.log4j.Logger;

/**
 * <p>Title: </p>
 *
 * <p>Description: 实现ICE日志接口，作为log4j的适配器</p>
 *
 * <p>Copyright: Copyright (c) 2004</p>
 *
 * <p>Company: nanwang</p>
 * @author yangj
 * @version 1.0
 */
public class ICELogger implements Ice.Logger {

  private Logger iLog = null;

  /**
   * ICELogger
   *
   * @param loger Log
   */
  public ICELogger(Logger loger) {
    iLog = loger;
  }

  /**
   * print
   *
   * @param string String
   */
  public void print(String string)
  {
    iLog.debug(string);
  }

  /**
   * trace
   *
   * @param string String
   * @param string1 String
   */
  public void trace(String string, String string1) {
    iLog.info(string+":"+string1);
  }

  /**
   * warning
   *
   * @param string String
   */
  public void warning(String string) {
    iLog.warn(string);
  }

  /**
   * error
   *
   * @param string String
   */
  public void error(String string) {
    iLog.error(string);
  }

  /**
   * equals
   *
   * @param rhs Object
   * @return boolean
   */
  public boolean equals(Object rhs) {
    if (!(rhs instanceof ICELogger))
    {
      return false;
    }
    return (this.iLog == ((ICELogger)rhs).iLog);
  }

  /**
   * clone
   *
   * @return Object
   */
  public Object clone() {
    return new ICELogger(iLog);
  }

  /**
   * ice_hash
   *
   * @return int
   */
  public int ice_hash() {
    return 0;
  }
}
