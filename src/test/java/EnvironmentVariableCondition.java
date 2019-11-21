import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;

public class EnvironmentVariableCondition {
    @Test
    @EnabledIfEnvironmentVariable(named = "ENV", matches = "staging-server")
    void onlyOnStagingServer() { /* validation code here*/ }

    @Test
    @DisabledIfEnvironmentVariable(named = "ENV", matches = ".*development.*")
    void notOnDeveloperWorkstation() { /* validation code here*/ }

}
