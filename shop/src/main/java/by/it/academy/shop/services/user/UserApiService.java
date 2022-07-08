package by.it.academy.shop.services.user;

import by.it.academy.shop.dtos.user.requests.AuthorizationUserRequest;
import by.it.academy.shop.dtos.user.requests.RegistrationUserRequest;
import by.it.academy.shop.entities.user.User;
import by.it.academy.shop.repositories.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Реализация сервиса обработки пользователя.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class UserApiService implements UserService {
    private final UserRepository userRepository;

    @Transactional
    @Override
    public User addUser(RegistrationUserRequest request) {
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        final User user = userAuthenticationService.registrationUser(userRepository.findAll(),
                request.getLogin(), request.getEmail(), request.getPassword());
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User authorizationUser(AuthorizationUserRequest request) {
        UserAuthenticationService userAuthenticationService = new UserAuthenticationService();
        return userAuthenticationService.authorizationUser(userRepository.findAll(), request.getLogin(), request.getPassword());
    }
}
