export class User {
    username: string = "";
    password: string = "";
    mentionHandle: string = "";
    description: string = "";
    following: User[];
    access_token: string = "";
}