const STATUSES = [
  'Не стоит играть в эту игру',
  'Мэг: Монстр Глубины',
  'Исследователь Марианской Впадины',
  'Вадик',
  'Помощник Вадика',
  'И так сойдет',
  'С кем не бывает',
  'Еще не все потеряно',
  'Хуже новичка',
  'Как Новичок',
  'Типа что-то Может',
  'Красавчик',
  'Хищник',
  'Убица Хищников',
  'Покоритель Эвереста',
  'Нет Друзей и Личной Жизни',
  'Бесстатусный'
]

export const getPlayerStatus = (rating) => {
  if (rating <= 5000) return STATUSES[0];
  if (rating > 5000 && rating <= 6000) return STATUSES[1];
  if (rating > 6000 && rating <= 7000) return STATUSES[2];
  if (rating > 7000 && rating <= 8000) return STATUSES[3];
  if (rating > 8000 && rating <= 8500) return STATUSES[4];
  if (rating > 8500 && rating <= 9000) return STATUSES[5];
  if (rating > 9000 && rating <= 9200) return STATUSES[6];
  if (rating > 9200 && rating <= 9500) return STATUSES[7];
  if (rating > 9500 && rating <= 9700) return STATUSES[8];
  if (rating > 9700 && rating <= 10000) return STATUSES[9];
  if (rating > 10000 && rating <= 11000) return STATUSES[10];
  if (rating > 11000 && rating <= 12000) return STATUSES[11];
  if (rating > 12000 && rating <= 13000) return STATUSES[12];
  if (rating > 13000 && rating <= 15000) return STATUSES[13];
  if (rating > 15000 && rating <= 20000) return STATUSES[14];
  if (rating > 20000) return STATUSES[15];
  return STATUSES[16];
}