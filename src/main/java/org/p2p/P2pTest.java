package org.p2p;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/8/10 9:32
 * @Description:
 */
public class P2pTest {

    /**
     * 【问题】写一张网页，用 WebRTC 实现点到点通信。
     * <p>
     * 【解析】这里我为你找到了一份 Github 上的源代码：https://github.com/ScaleDrone/webrtc。
     * <p>
     * <p>
     * <p>
     * 在 WebRTC 的网络世界中，视频传输可以走点到点服务。客户端被称作 Peer，Peer 的数据直接传送给另一个 Peer，我们也称作P2P 网络。在P2P 网络中，要解决 NAT 穿墙问题，WebRTC 设计了一个网络的抽象框架被称作交互式网络建立连接（Interactive Connectivity Establishment， ICE），图中的 STUN 是 ICE 的一个实现。
     * <p>
     * 对于一个 P2P 网络中的 Peer，它每次要接入这个 P2P 网络会获得一个身份，这个身份就包括它的 IP 地址、端口使用的协议等，这个身份被抽象成了一个对象——Candidate（候选人）。当候选人创建一个 P2P 连接的时候，它会获得候选人的身份。但这个时候，它还没有发起任何真实的数据连接。此时它必须知道另一个人的身份，才能够进行通信。
     * <p>
     * P2P 网络本身不具备传输身份的能力，因此这个时候需要另一个第三方网络提供身份的交换。代码中的这个第三方服务就是 ScaleDrone。当用户加入聊天室，会先创建连接：
     * <p>
     * pc = new RTCPeerConnection(...)
     * 接下来会触发onicecanddiate事件获得候选人（Candidate）身份：
     * <p>
     * pc.onicecandidate = event => {
     * if (event.candidate) {
     * // 通过ScaleDrone分发身份
     * }
     * };
     * 在实际的代码操作中，代码将获得的身份（event.candiate）直接发送到了 ScaleDrone 提供的某个聊天室中去，这样聊天室的其他用户就会拿到这个身份。
     * <p>
     * 当有新用户进入聊天室后，ScaleDrone 会广播新用户的身份：
     * <p>
     * 复制代码
     * room.on('data', (message, client) => {
     * // Message was sent by us
     * if (client.id === drone.clientId) {
     * return;
     * }
     * if (message.sdp) {
     * // This is called after receiving an offer or answer from another peer
     * pc.setRemoteDescription(new RTCSessionDescription(message.sdp), () => {
     * // When receiving an offer lets answer it
     * if (pc.remoteDescription.type === 'offer') {
     * pc.createAnswer().then(localDescCreated).catch(onError);
     * }
     * }, onError);
     * } else if (message.candidate) {
     * // Add the new ICE candidate to our connections remote description
     * pc.addIceCandidate(
     * new RTCIceCandidate(message.candidate), onSuccess, onError
     * );
     * }
     * });
     * 这个时候，用户彼此都会将对方加入自己的候选人列表：
     * <p>
     * pc.addIceCandidate(
     * new RTCIceCandidate(message.candidate), onSuccess, onError
     * )
     * 加入之后，如果远程候选人录制了视频，WebRTC 的 ontract 事件就会收到视频的数据流，也就是下面这段程序：
     * <p>
     * pc.ontrack = event => {
     * const stream = event.streams[0];
     * if (!remoteVideo.srcObject || remoteVideo.srcObject.id !== stream.id) {
     * remoteVideo.srcObject = stream;
     * }
     * };
     * 这份代码的优势是不需要提供中转的流媒体服务器，就可以完成点到点的视频通信。同理，如果是多人视频，也可以用同样的方法实现。这段程序中需要两个第三方的服务：
     * <p>
     * 基于 ICE 标准提供 P2P 网络的服务（提供 NAT 穿透能力），这个可以使用 STUN；
     * <p>
     * 第三方聊天室服务，用于实现聊天的具体逻辑和交换身份。
     */
    public static void main(String[] args) {

    }
}
