export const capitalizeString = (string) => string.charAt(0).toUpperCase() + string.slice(1).toLowerCase();

export const getTitleFromUrl = (url) => {
	const title = url.split('/')[1].replace('-', ' ');
	return capitalizeString(title);
}