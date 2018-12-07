import Config from 'react-global-configuration';

export const PRODUCTION = 'production';
export const DEV = 'development';

/**
 * Set global configuration according to the environment
 */
export default function () {
  let baseConfig = {
    login_callback: 'http://localhost:8080/login',
  };
  if (process.env.REACT_APP_ENV === PRODUCTION) {
    baseConfig = {
      ...baseConfig,
      login_callback: 'http://18.220.64.102:8080/login',
    };
  } else if (process.env.REACT_APP_ENV === DEV) {
    baseConfig = {
      ...baseConfig,
      login_callback: 'http://localhost:8080/login',
    };
  }
  // Freeze the configuration to prevent runtime modifications
  Config.set(baseConfig);
}