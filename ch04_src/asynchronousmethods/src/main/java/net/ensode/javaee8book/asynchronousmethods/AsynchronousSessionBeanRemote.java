/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.ensode.javaee8book.asynchronousmethods;

import java.util.concurrent.Future;
import javax.ejb.Remote;

/**
 *
 * @author David R. Heffelfinger <dheffelfinger@ensode.com>
 */
@Remote
public interface AsynchronousSessionBeanRemote {

  void slowMethod();

  Future<Long> slowMethodWithReturnValue();
    
}
