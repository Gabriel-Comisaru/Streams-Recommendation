# Smart Recommendation System For Streams

This project aims to implement a smart recommendation system for existing users of a well-known streaming application. The goal is to create a recommendation algorithm for streams (music, podcasts, or audiobooks) based on existing data about the application's users (listeners) and stream creators (musicians, podcast hosts, etc.) or data accumulated during the application's runtime. Details about the data format and the commands to be implemented are provided in the following sections.

# Application Functionality

To implement the recommendation system, data about each entity involved will be received in separate CSV files, as follows:

### Input Data
- Streamers: Data about stream authors, including musicians, podcast hosts, or audiobook authors, will be in the `streamers.csv` file.
- Streams: Data about posted streams in the application will be in the `streams.csv` file.
- Users: Data about users of the streaming platform will be in the `users.csv` file.

### Streamers
Data about stream authors will be in the `streamers.csv` file, where each line represents the data of a streamer with the format:
```
streamerType, id, name
```

### Streams
Data about posted streams will be in the `streams.csv` file, where each line represents the data of a stream:
```
streamType, id, streamGenre, noOfStreams, streamerId, length, dateAdded, name
```

### Users
Data about a user of the streaming platform will be in the `users.csv` file, where each line represents a user with the format:
```
id, name, streams
```

# Commands
To implement the smart recommendation system, the project must be capable of modifying existing data when commands are executed by users or streams. This ensures correct recommendations. The available commands for streamers and users are:

### Streamers' Commands:
- Add Stream
```
ADD <streamerId: Integer> <streamType: Integer> <id: Integer> <streamGenre: Integer> <length: Long> <name:String>
```
Modifies application data accordingly.
- List Streams by Streamer
```
LIST <streamerId:Integer>
```
Displays streams in JSON format.
- Delete Stream
```
DELETE <streamerId:Integer> <streamId: Integer>
```
Modifies application data accordingly.

### User's Commands:
- List Listening History
```
LIST <userId: Integer>
```
Displays listening history in JSON format.
- Listen to Stream
```
LISTEN <userId: Integer> <streamId:Integer>
```
Modifies application data accordingly.
- Recommend Top 5 Streams
```
RECOMMEND <userId: Integer> [SONG | PODCAST | AUDIOBOOK]
```
Displays recommended streams in JSON format.
- Recommend Surprise Streams
```
SURPRISE <userId: Integer> [SONG | PODCAST | AUDIOBOOK]
```
Displays surprise recommended streams in JSON format.
