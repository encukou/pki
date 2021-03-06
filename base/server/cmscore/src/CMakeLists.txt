project(pki-cmscore)

find_file(JSS_JAR
    NAMES
        jss4.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

find_file(LDAPJDK_JAR
    NAMES
        ldapjdk.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

find_file(COMMONS_CODEC_JAR
    NAMES
        commons-codec.jar
    PATHS
        /usr/share/java
)

find_file(COMMONS_HTTPCLIENT_JAR
    NAMES
        commons-httpclient.jar
    PATHS
        /usr/share/java
)

find_file(APACHE_COMMONS_LANG_JAR
    NAMES
        apache-commons-lang.jar
    PATHS
        /usr/share/java
)

find_file(TOMCAT_CATALINA_JAR
    NAMES
        catalina.jar
    PATHS
        /usr/share/java/tomcat
)

find_file(TOMCAT_UTIL_JAR
    NAMES
        tomcat-util.jar
    PATHS
        /usr/share/java/tomcat
)

find_file(SERVLET_JAR
    NAMES
        servlet.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

find_file(VELOCITY_JAR
    NAMES
        velocity.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

find_file(XALAN_JAR
    NAMES
        xalan-j2.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

find_file(XERCES_JAR
    NAMES
        xerces-j2.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

find_file(JAXRS_API_JAR
    NAMES
        jaxrs-api.jar
    PATHS
        ${RESTEASY_LIB}
)

find_file(RESTEASY_JAXRS_JAR
    NAMES
        resteasy-jaxrs.jar
    PATHS
        ${RESTEASY_LIB}
)

find_file(RESTEASY_ATOM_PROVIDER_JAR
    NAMES
        resteasy-atom-provider.jar
    PATHS
        ${RESTEASY_LIB}
)

find_file(HTTPCLIENT_JAR
    NAMES
        httpclient.jar
    PATHS
        /usr/share/java/httpcomponents
)

find_file(HTTPCORE_JAR
    NAMES
        httpcore.jar
    PATHS
        /usr/share/java/httpcomponents
)

find_file(NUXWDOG_JAR
    NAMES
        nuxwdog.jar
    PATHS
        ${JAVA_LIB_INSTALL_DIR}
        /usr/share/java
)

# build pki-cmscore
javac(pki-cmscore-classes
    SOURCES
        com/netscape/cmscore/*.java
    CLASSPATH
        ${PKI_NSUTIL_JAR} ${PKI_CMSUTIL_JAR} ${PKI_CERTSRV_JAR} ${PKI_CMS_JAR} ${PKI_TOMCAT_JAR}
        ${LDAPJDK_JAR} ${SERVLET_JAR} ${VELOCITY_JAR} ${XALAN_JAR} ${XERCES_JAR}
        ${JSS_JAR} ${COMMONS_CODEC_JAR} ${COMMONS_HTTPCLIENT_JAR}
        ${APACHE_COMMONS_LANG_JAR}
        ${TOMCAT_CATALINA_JAR} ${TOMCAT_UTIL_JAR} ${SYMKEY_JAR}
        ${JAXRS_API_JAR} ${RESTEASY_JAXRS_JAR} ${RESTEASY_ATOM_PROVIDER_JAR}
        ${HTTPCLIENT_JAR} ${HTTPCORE_JAR}
        ${NUXWDOG_JAR}
    OUTPUT_DIR
        ${CMAKE_BINARY_DIR}/classes
    DEPENDS
        pki-nsutil-jar pki-cmsutil-jar pki-certsrv-jar pki-cms-jar pki-tomcat-jar
)

configure_file(
    ${CMAKE_CURRENT_SOURCE_DIR}/pki-cmscore.mf
    ${CMAKE_CURRENT_BINARY_DIR}/pki-cmscore.mf
)

jar(pki-cmscore-jar
    CREATE
        ${CMAKE_BINARY_DIR}/dist/pki-cmscore.jar
    OPTIONS
        m
    PARAMS
        ${CMAKE_CURRENT_BINARY_DIR}/pki-cmscore.mf
    INPUT_DIR
        ${CMAKE_BINARY_DIR}/classes
    FILES
        com/netscape/cmscore/*.class
    DEPENDS
        pki-cmscore-classes
)

install(
    FILES
        ${CMAKE_BINARY_DIR}/dist/pki-cmscore.jar
    DESTINATION
        ${JAVA_JAR_INSTALL_DIR}/pki
)

set(PKI_CMSCORE_JAR ${CMAKE_BINARY_DIR}/dist/pki-cmscore.jar CACHE INTERNAL "pki-cmscore jar file")
