package com.ice.exception;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: nanwang
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */
public class NanwangException
    extends Exception
{

  private String name = "com.nanwang.exception.NanwangException";

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public NanwangException()
  {
    super();
  }

  public NanwangException(String msg)
  {
    super(msg);
  }

  public NanwangException(Throwable e)
  {
    super(e);
  }

  public NanwangException(String msg, Throwable e)
  {
    super(msg, e);
  }
}
