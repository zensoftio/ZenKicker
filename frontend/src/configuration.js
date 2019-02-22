import Config from 'react-global-configuration';

/**
 * Set global configuration according to the environment
 */
export default function () {
  const baseConfig = {
    login_callback: '/login',
    signUp_callback: '/sign-up',
  };

  Config.set(baseConfig);
}