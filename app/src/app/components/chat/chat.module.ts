import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FlexLayoutModule} from "@angular/flex-layout";
import {ConversationsComponent} from "./conversations/conversations.component";
import {MessagesComponent} from "./messages/messages.component";
import {ChatRoutingModule} from "./chat-routing.module";

@NgModule({
  declarations: [
    ConversationsComponent,
    MessagesComponent,
  ],
  imports: [
    ChatRoutingModule,
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
  ]
})

export class ChatModule {}
