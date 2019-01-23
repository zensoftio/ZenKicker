import Config from 'react-global-configuration';

export const PRODUCTION = 'production';
export const DEV = 'development';

/**
 * Set global configuration according to the environment
 */
export default function () {
  let baseConfig = {
    login_callback: 'http://localhost:8080/login',
    signUp_callback: 'http://localhost:8080/sign-up',
  };
  if (process.env.REACT_APP_ENV === PRODUCTION) {
    baseConfig = {
      ...baseConfig,
      login_callback: 'http://new-kicker.zensoft.by/login',
      signUp_callback: 'http://new-kicker.zensoft.by/sign-up',
    };
  } else if (process.env.REACT_APP_ENV === DEV) {
    baseConfig = {
      ...baseConfig,
      login_callback: 'http://localhost:8080/login',
      signUp_callback: 'http://localhost:8080/sign-up',
    };
  }
  // Freeze the configuration to prevent runtime modifications
  Config.set(baseConfig);
}