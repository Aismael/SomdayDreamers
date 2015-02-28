/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.graphikCore;

import com.jme3.renderer.queue.RenderQueue;

/**
 *
 * @author Aismael
 */

public class GTAPObject {
    private String lightningmodelpath;//param
    private String diffuspath;//param
    private String normalpath;//param
    private String specularpath;//param
    private String modelpath;//param
    private RenderQueue.ShadowMode Shadowmode;//param
    private String translucentpath;

    public GTAPObject(String diffuspath,String normalpath,String specularpath,String modelpath){
        this.diffuspath=diffuspath;
        this.normalpath=normalpath;
        this.specularpath=specularpath;
        this.modelpath=modelpath;
        Shadowmode=RenderQueue.ShadowMode.CastAndReceive;
        lightningmodelpath="Common/MatDefs/Light/Lighting.j3md";
    }

    public void setDiffuspath(String diffuspath) {
        this.diffuspath = diffuspath;
    }

    public void setLightningmodelpath(String lightningmodelpath) {
        this.lightningmodelpath = lightningmodelpath;
    }

    public void setModelpath(String modelpath) {
        this.modelpath = modelpath;
    }

    public void setNormalpath(String normalpath) {
        this.normalpath = normalpath;
    }

    public void setShadowmode(RenderQueue.ShadowMode Shadowmode) {
        this.Shadowmode = Shadowmode;
    }

    public void setSpecularpath(String specularpath) {
        this.specularpath = specularpath;
    }

    public void setTranslucentpath(String translucentpath) {
        this.translucentpath = translucentpath;
    }

    public String getDiffuspath() {
        return diffuspath;
    }

    public String getLightningmodelpath() {
        return lightningmodelpath;
    }

    public String getModelpath() {
        return modelpath;
    }

    public String getNormalpath() {
        return normalpath;
    }

    public RenderQueue.ShadowMode getShadowmode() {
        return Shadowmode;
    }

    public String getSpecularpath() {
        return specularpath;
    }

    public String getTranslucentpath() {
        return translucentpath;
    }
    
    
    
}
