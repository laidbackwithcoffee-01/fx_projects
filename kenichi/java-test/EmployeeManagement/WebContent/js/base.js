/**
 * 戻るボタン禁止
 * @returns
 */
function disabled_back() {
	history.pushState(null, null, null);

	window.addEventListener("popstate", function() {
	    history.pushState(null, null, null);
	});
}