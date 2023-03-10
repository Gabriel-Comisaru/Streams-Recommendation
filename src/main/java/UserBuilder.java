import java.util.List;

public class UserBuilder {
    private final User user = new User();

    public UserBuilder id(Integer id) {
        user.setId(id);
        return this;
    }

    public UserBuilder name(String name) {
        user.setName(name);
        return this;
    }

    public UserBuilder streams(List<Integer> streams) {
        user.setStreams(streams);
        return this;
    }

    public User build() {
        return user;
    }
}
