package er.indexing.example;
// Generated by the WOLips Templateengine Plug-in at 12.06.2008 11:20:01

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WORequest;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;

import er.directtoweb.ERD2WDirectAction;
import er.extensions.eof.ERXEC;
import er.indexing.ERIndex;
import er.indexing.example.eof.Asset;
import er.indexing.example.eof.AssetGroup;
import er.indexing.example.eof.Tag;

public class DirectAction extends ERD2WDirectAction {

    public DirectAction(WORequest aRequest) {
        super(aRequest);
    }
   
    /**
     * Checks if a page configuration is allowed to render.
     * Provide a more intelligent access scheme as the default just returns false. And
     * be sure to read the javadoc to the super class.
     * @param pageConfiguration
     * @return
     */
    protected boolean allowPageConfiguration(String pageConfiguration) {
        return true;
    }

    public WOActionResults defaultAction() {
        EOEditingContext ec = ERXEC.newEditingContext();
        ec.lock();
        try {
            Tag tag = Tag.clazz.allObjects(ec).lastObject();
            Asset asset = Asset.clazz.allObjects(ec).lastObject();
            AssetGroup assetGroup = AssetGroup.clazz.allObjects(ec).lastObject();
            // new DataCreator().createDummyData();
            ERIndex eofStore = ERIndex.indexNamed("AssetInEOFStore");
            ERIndex fileStore = ERIndex.indexNamed("AssetInFileStore");
            EOQualifier tagQualifier = new EOKeyValueQualifier("tags.name", EOQualifier.QualifierOperatorEqual, tag.name());
            EOQualifier groupQualifier = new EOKeyValueQualifier("assetGroup.name", EOQualifier.QualifierOperatorEqual, tag.name());
            log.info("fileStore: " + fileStore.findGlobalIDs(tagQualifier).count());
            log.info("eofStore: " + eofStore.findGlobalIDs(tagQualifier).count());
            log.info("fileStore: " + fileStore.findGlobalIDs(groupQualifier).count());
            log.info("eofStore: " + eofStore.findGlobalIDs(groupQualifier).count());
            
            String newName = "cooltest";
            tagQualifier = new EOKeyValueQualifier("tags.name", com.webobjects.eocontrol.EOQualifier.QualifierOperatorEqual, newName);
            tag.setName(newName + " " + System.currentTimeMillis());
            ec.saveChanges();
            
            assetGroup.setName(newName + "  " + System.currentTimeMillis());
            ec.saveChanges();
            log.info("fileStore 1: " + fileStore.findGlobalIDs(tagQualifier).count());
            log.info("eofStore 1: " + eofStore.findGlobalIDs(tagQualifier).count());
            try {
                if(true) {
                    Thread.sleep(2000);
                }
                log.info("fileStore 2: " + fileStore.findGlobalIDs(tagQualifier).count());
                log.info("eofStore 2: " + eofStore.findGlobalIDs(tagQualifier).count());
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } finally {
            ec.unlock();
        }
        return pageWithName(Main.class.getName());
    }
}