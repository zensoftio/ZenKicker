import React from 'react';
import {getTitleFromUrl} from "../../helpers/string-helpers";
import {MainTitle} from "../../components/main-title";

const PlayoffsScene = ({match}) => (
	<div>
		<MainTitle>{getTitleFromUrl(match.url)}</MainTitle>
	</div>
)

export default PlayoffsScene;