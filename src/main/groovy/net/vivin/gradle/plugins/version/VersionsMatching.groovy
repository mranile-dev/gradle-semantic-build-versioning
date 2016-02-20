package net.vivin.gradle.plugins.version

import org.gradle.tooling.BuildException

import java.util.regex.Pattern

/**
 * Created on 2/9/16 at 10:33 PM
 * @author vivin
 */
class VersionsMatching {
    int major = -1
    int minor = -1
    int patch = -1

    void validate() {
        if((major < 0 && minor >= 0) || (minor < 0 && patch >= 0)) {
            throw new BuildException("When specifying a matching version-component, all preceding components (if any) must also be specified ", null)
        }
    }

    Pattern toPattern() {
        Pattern pattern = ~/.*/

        if(major >= 0) {
            pattern = ~"${major}\\."

            if(minor >= 0) {
                pattern = ~"${pattern}${minor}\\."

                if(patch >= 0) {
                    pattern = ~"${pattern}${patch}"
                }
            }
        }

        return pattern
    }
}