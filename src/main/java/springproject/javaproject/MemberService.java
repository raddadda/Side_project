package springproject.javaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public UUID join(MemberForm memberform){
        Member member = new Member();
        member.setName(memberform.getName());
        member.setPassword(memberform.getPassword());
        member.setEmail(memberform.getEmail());
        member.setNickname(memberform.getNickname());
        if(ValidDuplicateMemberByNickname(member.getNickname())){
            throw new IllegalArgumentException("닉네임이 이미 사용 중입니다.");
        }
        if(ValidDuplicateMemberByEmail(member.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 회원의 이메일입니다.");
        }
        memberRepository.save(member);
        return member.getId();
    }

    private boolean ValidDuplicateMemberByNickname(String nickname){
        Optional<Member> member = memberRepository.findByNickname(nickname);
        return member.isPresent();
    }

    private boolean ValidDuplicateMemberByEmail(String email){
        Optional<Member> member = memberRepository.findByNickname(email);
        return member.isPresent();
    }



}
