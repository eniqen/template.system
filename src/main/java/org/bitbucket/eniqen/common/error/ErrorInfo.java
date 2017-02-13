package org.bitbucket.eniqen.common.error;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public interface ErrorInfo {

    default int getCode(){
        return 0;
    }

    default String getStatus(){
        return "Empty";
    }
}
