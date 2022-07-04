export class Message {
    constructor( 
        public id: string,
        public senderId: string,
        public receiverId: string,
        public text: string,
        public messageSent: Date|null
    ) {}
}