
import hudson.tasks.Maven
import hudson.tasks.Maven.MavenInstallation;
import hudson.tools.InstallSourceProperty;
import hudson.tools.ToolProperty;
import hudson.tools.ToolPropertyDescriptor
import hudson.tools.ZipExtractionInstaller;
import hudson.util.DescribableList
import jenkins.model.Jenkins;

def extensions = Jenkins.instance.getExtensionList(Maven.DescriptorImpl.class)[0]

List<MavenInstallation> installations = []

println("Setting up tool: maven3 ")

installations.add(new MavenInstallation("maven3", "/usr/share/maven"))

extensions.setInstallations(installations.toArray(new MavenInstallation[installations.size()]))
extensions.save()

