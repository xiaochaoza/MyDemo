package com.fzzz.design_mode.proxy.dynamic_proxy;

/**
 * description:
 * author: ShenChao
 * time: 2019-06-19
 * update:
 */
public class CheckUpdate {
    private boolean hasUpdate;
    private String newVersion;

    public boolean isHasUpdate() {
        return hasUpdate;
    }

    public void setHasUpdate(boolean hasUpdate) {
        this.hasUpdate = hasUpdate;
    }

    public String getNewVersion() {
        return newVersion;
    }

    public void setNewVersion(String newVersion) {
        this.newVersion = newVersion;
    }

    @Override
    public String toString() {
        return "Has update : " + hasUpdate + " ; The newest version is : " + newVersion;
    }
}
