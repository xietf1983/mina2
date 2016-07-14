// **********************************************************************
//
// Copyright (c) 2003-2008 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.0

package GeStorm;

public interface WebSenderPrx extends Ice.ObjectPrx
{
    public void trigScheme(String triggerDescribe, TScheme[] schemes, String[] userIds);
    public void trigScheme(String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx);

    public boolean trigScheme_async(AMI_WebSender_trigScheme __cb, String triggerDescribe, TScheme[] schemes, String[] userIds);
    public boolean trigScheme_async(AMI_WebSender_trigScheme __cb, String triggerDescribe, TScheme[] schemes, String[] userIds, java.util.Map<String, String> __ctx);
}
