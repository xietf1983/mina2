package com.ice.exception;

/**
 * <p>Title: </p>
 * <p>Description: 用户导致的异常</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: nanwang</p>
 * @author not attributable
 * @version 1.0
 */

public class UserCausedException
  extends NanwangException
{
  private String tip;

  public UserCausedException()
  {
    super();
  }

  public UserCausedException(String msg, Throwable e,String asTip)
  {
    super(msg, e);
    this.tip = asTip;
  }
  public UserCausedException(String msg, Throwable e)
  {
    super(msg, e);
    this.tip = msg;
  }

  public UserCausedException(Throwable e)
  {
    super(e);
  }

  public UserCausedException(String msg)
  {
    super(msg);
    this.tip = msg;
  }

  public void setTip(String asTip)
  {
    this.tip = asTip;
  }

  public String getTip()
  {
    return tip;
  }
}
