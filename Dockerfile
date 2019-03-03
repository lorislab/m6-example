FROM jboss/wildfly:16.0.0.Final as build

# Switch the wildfly configuration to the standalone-full.xml
RUN cd /opt/jboss/wildfly/standalone/configuration/ \
    && rm standalone.xml \
    && cp standalone-full.xml standalone.xml

# Reconfigure the wildfly
COPY src/main/docker/ /tmp/docker
RUN /opt/jboss/wildfly/bin/jboss-cli.sh --file=/tmp/docker/wildfly/config.cli

FROM jboss/wildfly:16.0.0.Final

# Copy configuration from the build image
COPY --chown=jboss:root --from=build /opt/jboss/wildfly/standalone/configuration/standalone.xml /opt/jboss/wildfly/standalone/configuration/standalone.xml

# Deploy the application
ADD target/*.war /opt/jboss/wildfly/standalone/deployments/

# Update the database and start the server
CMD /opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.bind.address=$(hostname -i) -Djboss.bind.address.private=$(hostname -i)
