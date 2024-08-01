# LogMasterExpansion

**LogMaster** is a useful and easy-to-use PlaceholderAPI extension.

His job is to log the player's favorite events with his text, the date of the event, and the player's nickname.

It can be used in various plugins that support the PlaceholderAPI.
For example, let's take the Donat Case plugin. If we want to log the event when the player opened the case, then we add a placeholder to the case opening notification:(%logmaster_log_NICKNAME_YOUR TEXT% Example: %logmaster_log_gmpady_Player {player} opened the donate case at {date}%).

We can also use login in various menu plugins that support PlaceholderAPI, we just need to add a log placeholder in the text of any item!

Also, the LogMaster extension supports UNICODE in the text ({unicode+XXXX}) OR ({unicode+XXXXXX})!
Example:
{unicode+2665} is ♥️
For logging, it will be used as follows %logmaster_log_NICKNAME_{player} opened the menu {unicode+2665}%

You can also get all player logs using one placeholder!
Example %logmaster_get_NICKNAME%

All player logs are in different "yaml" files in the "LogMaster" directory.

**Hope you enjoy this extension, thanks for your attention!**
