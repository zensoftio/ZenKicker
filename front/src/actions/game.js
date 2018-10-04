import Paths from "../dicts/paths";
import {api} from "../config/api";

export const registerGame = async (data) => {
  return await api.post(Paths.Game.RegisterGame, data);
}
