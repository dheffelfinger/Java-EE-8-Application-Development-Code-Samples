package net.ensode.javaee8book.singletonsession;

import java.util.List;
import javax.ejb.Remote;
import net.ensode.javaee8book.entity.UsStates;

/**
 *
 * @author heffel
 */
@Remote
public interface SingletonSessionBeanRemote {

    List<UsStates> getStateList();
   
}
