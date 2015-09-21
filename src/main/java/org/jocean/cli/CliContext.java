package org.jocean.cli;

import org.jocean.idiom.Propertyable;


/**
 * @author Marvin.Ma
 *
 */
public interface CliContext extends Propertyable<CliContext> {
	public CommandRepository getCommandRepository();
}
