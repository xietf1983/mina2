// **********************************************************************
//
// Copyright (c) 2003-2008 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.3.0

package GeMss;

public interface _MssWebOperations
{
    void QueryAlarmStorage_async(AMD_MssWeb_QueryAlarmStorage __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, Ice.Current __current);

    void QueryAlarmStorageV2_async(AMD_MssWeb_QueryAlarmStorageV2 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long alarmTime, String strUserIP, Ice.Current __current);

    void QueryAlarmStorageV3_async(AMD_MssWeb_QueryAlarmStorageV3 __cb, boolean bCenter, TFdInfoKey tFdKey, String strAlarmGuid, long nTime, String strUserIP, String storageAreaId, Ice.Current __current);
}
