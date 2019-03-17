package handlers;

import interfaces.IRestRepository;
import models.User;
import repositories.IUserRepository;

public class UserService extends CRUDService<User, Integer> {

    private IUserRepository repository;

    @Override
    public void initRepository() {
        setRepo((IRestRepository) repository);
    }

    @Override
    public Class<? extends CRUDService<User, Integer>> getClazz() {
        return this.getClass();
    }
}
