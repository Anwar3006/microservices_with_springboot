/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */

package microservices_book.gamification_service.test_service;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import microservices_book.gamification_service.domain.LeaderBoardRow;
import microservices_book.gamification_service.service.LeaderBoardService;
 
/**
 *
 * @author anwa
 */
public class LeaderBoardServiceImplUnitTest {

    @Mock
    private LeaderBoardService leaderBoardService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getLeaderBoardFirst10Test() throws Exception {
        // given
        LeaderBoardRow row1 = new LeaderBoardRow();
        LeaderBoardRow row2 = new LeaderBoardRow();
        given(leaderBoardService.getLeaderBoardFirst10Rows()).willReturn(Lists.list(row1, row2));

        // when
        List<LeaderBoardRow> leaderBoardRows = leaderBoardService.getLeaderBoardFirst10Rows(); 

        // then
        assert(leaderBoardRows.size() == 2);
        assertThat(leaderBoardRows).contains(row1, row2);
    }
}