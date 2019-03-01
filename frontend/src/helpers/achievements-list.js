import {AchievementType, AchievementLevel} from '../constants';

import goldStar from "../shared/images/icons/gold-star.png";
import silverStar from "../shared/images/icons/silver-star.png";
import bronzeStar from "../shared/images/icons/bronze-star.png";

export const mappedAchievementsList = (list) => {
  const weekEndOverallRatingGold = list.filter(
    i => i.type === AchievementType.WEEK_END_OVERALL_RATING && i.level === AchievementLevel.GOLD);
  const weekEndOverallRatingSilver = list.filter(
    i => i.type === AchievementType.WEEK_END_OVERALL_RATING && i.level === AchievementLevel.SILVER);
  const weekEndOverallRatingBronze = list.filter(
    i => i.type === AchievementType.WEEK_END_OVERALL_RATING && i.level === AchievementLevel.BRONZE);

  return ([{
    type: `${AchievementType.WEEK_END_OVERALL_RATING}_${AchievementLevel.GOLD}`,
    list: weekEndOverallRatingGold,
    icon: goldStar
  }, {
    type: `${AchievementType.WEEK_END_OVERALL_RATING}_${AchievementLevel.SILVER}`,
    list: weekEndOverallRatingSilver,
    icon: silverStar
  }, {
    type: `${AchievementType.WEEK_END_OVERALL_RATING}_${AchievementLevel.BRONZE}`,
    list: weekEndOverallRatingBronze,
    icon: bronzeStar
  }]);
};
