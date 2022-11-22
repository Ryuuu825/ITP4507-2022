package com.BMS.Command.Common;

/**
 * This class is used to store the meta information of a command.
 */
// public record CmdMetaInfo(String desc, com.BMS.Command.Factory.Factory factory) {


//     public com.BMS.Command.Factory.Factory getFactory() {
//         return factory;
//     }

// }


public class CmdMetaInfo {
    private String desc;
    private com.BMS.Command.Factory.Factory factory;

    public CmdMetaInfo(String desc, com.BMS.Command.Factory.Factory factory) {
        this.desc = desc;
        this.factory = factory;
    }

    public String desc() {
        return desc;
    }

    public com.BMS.Command.Factory.Factory getFactory() {
        return factory;
    }
}
