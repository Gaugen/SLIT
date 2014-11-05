package no.uia.slit.bean;

import no.uia.slit.ejb.ModuleEJB;
import no.uia.slit.entity.Student;
import no.uia.slit.entity.SlitFile;
import no.uia.slit.entity.Module;
import no.uia.slit.entity.Assessment;
/*
*import is202.hrms.entity.Role;
*/
import no.uia.slit.web.View;
import no.uia.slit.filetransfer.FileUploadBean;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author evenal
 */
@Named("modulebean")
@ConversationScoped
public class ModuleBean implements Serializable {

    private static final long serialVersionUID = 42L;
    @Inject
    private Conversation conv;
    @Inject
    private FileUploadBean filebean;

    @EJB
    private ModuleEJB modEjb;

    private Module mod;
    private boolean updating;

    // ****
    private long pid;

    public ModuleBean() {
        updating = false;
        mod = null;
    }

    public boolean isUpdating() {
        return updating;
    }

    public long getPid() {
        return pid;
    }

    public void setPid(long pid) {
        if (conv.isTransient()) {
            conv.begin();
        }
        mod = modEjb.find(pid);
        if (null == mod) {
            mod = new Module();
            this.pid = 0;
            updating = false;
        } else {
            this.pid = mod.getModuleNo();
            updating = true;
        }
    }

    public String getModuleName() {
        return mod.getModuleName();
    }

    public void setModuleName(String moduleName) {
        mod.setModuleName(moduleName);
    }

    public List<Assessment> getParticipant() {
        return modEjb.getParticipant(pid);
    }

    public View addParticipant(Student stu, Role role) {
        return View.module;
    }

    public View removeParticipant(Student stu) {
        if (null != stu) {
            System.err.println("Removing " + stu);
            modEjb.removeMember(pid, mod.getId());
        }

        return View.module;
    }

    public View saveProject() {
        if (updating) {
            modEjb.update(mod);
        } else {
            modEjb.insert(mod);
        }
        conv.end();
        return View.modules;
    }

    public View deleteModule() {
        modEjb.delete(mod);
        conv.end();
        return View.modules;
    }

    public View setFile() {
        System.out.println("ModuleBean.setFile()");
        System.out.println("module is " + mod);
        SlitFile file = filebean.getFile();
        System.out.println("file is " + file);
        modEjb.setModuleFile(mod, file);
        return View.module;
    }

    public long getFileId() {
        if (mod != null) {
            SlitFile plan = mod.getFile();
            if (null == file) {
                return 0;
            } else {
                return file.getId();
            }
        }
        return 0;
    }
}