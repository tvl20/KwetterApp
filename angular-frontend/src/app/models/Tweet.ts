import { User } from './User';

export class Tweet {
    postDate: string;
    message: string;
    poster: User;

    constructor (postDate: string, message: string, poster: User)
    {
        this.postDate = postDate;
        this.message = message;
        this.poster = poster;
    }
}